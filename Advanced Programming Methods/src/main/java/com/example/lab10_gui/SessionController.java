package com.example.lab10_gui;

import com.example.lab10_gui.business.UserService;
import com.example.lab10_gui.entities.*;
import com.example.lab10_gui.exceptions.RepositoryError;
import com.example.lab10_gui.exceptions.ValidationError;
import com.example.lab10_gui.mappers.FriendshipToFriendRequestDTOMapper;
import com.example.lab10_gui.mappers.MessageToMessageDTOMapper;
import com.example.lab10_gui.mappers.UserToUserSearchResultMapper;
import com.example.lab10_gui.observer_pattern.ChangeEventType;
import com.example.lab10_gui.observer_pattern.Observer;
import com.example.lab10_gui.observer_pattern.UserEntityChangeEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


public class SessionController implements Observer<UserEntityChangeEvent> {
    private final ObservableList<UserSearchResult> searchModel = FXCollections.observableArrayList();
    private final UserToUserSearchResultMapper userToSearchResultMapper = new UserToUserSearchResultMapper();
    private final FriendshipToFriendRequestDTOMapper friendshipToFriendRequestDTOMapper = new FriendshipToFriendRequestDTOMapper();
    private final MessageToMessageDTOMapper messageToMessageDTOMapper = new MessageToMessageDTOMapper();
    @FXML
    public VBox rightSectionVbox;
    @FXML
    public HBox underTableHbox;
    @FXML
    public HBox searchBar;
    private User userLoggedIn;
    private UserService userService;
    @FXML
    private Button sessionExitButton;
    @FXML
    private TextField searchFriendTextField;
    @FXML
    private Label statusBarLabel;
    @FXML
    private TableView<UserSearchResult> searchResultTableView;
    @FXML
    private TableColumn<UserSearchResult, String> firstNameColumn;
    @FXML
    private TableColumn<UserSearchResult, String> lastNameColumn;
    @FXML
    private TableColumn<UserSearchResult, String> usernameColumn;
    @FXML
    private TableColumn<UserSearchResult, HBox> actionColumn;
    @FXML
    private Pane statusBarRightPane;
    @FXML
    private Button statusBarButton;
    @FXML
    private HBox underTableMessagePrompt;
    @FXML
    private TextField messageTextField;

    private TableView<FriendRequestDTO> friendshipsRequestsTableView;
    private TableColumn<FriendRequestDTO, String> requestStatusColumn;
    private TableColumn<FriendRequestDTO, String> requestUsernameColumn;
    private ListView<HBox> conversationArea;
    private Button newConversationButton;
    private User currentConversationPartner;


    public void initialize(UserService userService, User user) {
        this.userService = userService;
        this.userService.addObserver(this);

        this.userToSearchResultMapper.setFriendshipDbRepository(this.userService.getFriendshipDbRepository());
        this.userToSearchResultMapper.setUserWhoSearches(user);

        this.friendshipToFriendRequestDTOMapper.setUserDbRepository(this.userService.getUserDbRepository());

        this.messageToMessageDTOMapper.setUserDbRepository(this.userService.getUserDbRepository());

        this.userLoggedIn = user;
    }

    public void onExitSession(ActionEvent actionEvent) {
        Stage stage = (Stage) this.sessionExitButton.getScene().getWindow();
        stage.close();
    }

    public void onSearchUser(ActionEvent actionEvent) {
        String search_filter = this.searchFriendTextField.getText();
        Predicate<UserSearchResult> userPredicate = userSearchResult -> Objects.equals(userSearchResult.getUsername(), search_filter);

        Iterable<User> userIterable = this.userService.get_all_users();
        List<User> userList = new ArrayList<>();
        for (User user : userIterable) {
            userList.add(user);
        }

        List<UserSearchResult> userSearchResultList = this.userToSearchResultMapper.convert(userList);
        List<UserSearchResult> searchResults = userSearchResultList.stream().filter(userPredicate).toList();
        searchResultTableView.getItems().setAll(searchResults);
    }

    private void add_message_prompt() {
        try {
            this.rightSectionVbox.getChildren().remove(this.underTableHbox);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.underTableMessagePrompt);
        } catch (Exception e) {
        }
        this.rightSectionVbox.getChildren().add(this.underTableMessagePrompt);
        this.rightSectionVbox.getChildren().add(this.underTableHbox);
    }

    public void setConversationPartner (User conversationPartner) {
        this.currentConversationPartner=conversationPartner;
        this.load_conversation();
    }
    public void load_conversation() {
        this.add_message_prompt();
        this.messageToMessageDTOMapper.setSender(this.userLoggedIn);
        this.messageToMessageDTOMapper.setReceiver(this.currentConversationPartner);

        if (currentConversationPartner !=null) {
            List<Message> messages = this.userService.get_all_messages_between_two_users(userLoggedIn, this.currentConversationPartner);
            List<HBox> messagesAsText = this.messageToMessageDTOMapper.convert(messages);
            this.conversationArea.getItems().setAll(messagesAsText);
        }
    }

    private void getFriendRequests() {
        List<Friendship> friendshipList = this.userService.get_friend_requests_belonging_to_a_user(this.userLoggedIn);
        List<FriendRequestDTO> requests = friendshipToFriendRequestDTOMapper.convert(friendshipList);
        friendshipsRequestsTableView.getItems().setAll(requests);
    }

    private void getSearchResult() {
        String search_filter = this.searchFriendTextField.getText();
        Predicate<UserSearchResult> userPredicate = userSearchResult -> userSearchResult.getFirstName().contains(search_filter) || userSearchResult.getLastName().contains(search_filter) || userSearchResult.getUsername().contains(search_filter);

        Iterable<User> userIterable = this.userService.get_all_users();
        List<User> userList = new ArrayList<>();
        for (User user : userIterable) {
            userList.add(user);
        }

        List<UserSearchResult> userSearchResultList = this.userToSearchResultMapper.convert(userList);
        List<UserSearchResult> searchResults = userSearchResultList.stream().filter(userPredicate).toList();
        searchResultTableView.getItems().setAll(searchResults);

        for (UserSearchResult sr : searchResults) {
            if (sr.getFriendshipStatus() == FriendshipStatus.NON_EXISTENT) {
                //send friend request
                sr.getButton1().setOnAction(ev -> {
                    try {
                        this.userService.send_friend_request(this.userLoggedIn.getId(), sr.getId());
                    } catch (ValidationError e) {
                        throw new RuntimeException(e);
                    } catch (RepositoryError e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else if (sr.getFriendshipStatus() == FriendshipStatus.SENT) {
                sr.getButton1().setOnAction(event -> {
                    //remove friend request
                    try {
                        this.userService.remove_friend_request(this.userLoggedIn.getId(), sr.getId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else if (sr.getFriendshipStatus() == FriendshipStatus.PENDING) {
                sr.getButton1().setOnAction(event -> {
                    //accept friend request
                    try {
                        this.userService.accept_friend_request(this.userLoggedIn.getId(), sr.getId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                sr.getButton2().setOnAction(event -> {
                    //reject friend request
                    try {
                        this.userService.remove_friendship(this.userLoggedIn.getId(), sr.getId());
                    } catch (ValidationError e) {
                        throw new RuntimeException(e);
                    } catch (RepositoryError e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else if (sr.getFriendshipStatus() == FriendshipStatus.ACCEPTED) {
                sr.getButton1().setOnAction(event -> {
                    //accept friend request
                    try {
                        this.userService.remove_friendship(this.userLoggedIn.getId(), sr.getId());
                    } catch (ValidationError e) {
                        throw new RuntimeException(e);
                    } catch (RepositoryError e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        //searchResultTableView.getItems().setAll(userSearchResultList);

        //searchModel.setAll(userSearchResultList.stream().filter(userPredicate).collect(Collectors.toList()));
    }

    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<UserSearchResult, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<UserSearchResult, String>("lastName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<UserSearchResult, String>("username"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<UserSearchResult, HBox>("buttonBox"));
        searchResultTableView.setItems(this.searchModel);

        searchFriendTextField.textProperty().addListener(f -> getSearchResult());

        this.friendshipsRequestsTableView = new TableView<FriendRequestDTO>();
        this.requestStatusColumn = new TableColumn<FriendRequestDTO, String>();
        this.requestUsernameColumn = new TableColumn<FriendRequestDTO, String>();
        this.requestUsernameColumn.setCellValueFactory(new PropertyValueFactory<FriendRequestDTO, String>("username"));
        this.requestStatusColumn.setCellValueFactory(new PropertyValueFactory<FriendRequestDTO, String>("status"));
        this.requestUsernameColumn.setText("USERNAME");
        this.requestStatusColumn.setText("STATUS");
        this.friendshipsRequestsTableView.getColumns().add(this.requestUsernameColumn);
        this.friendshipsRequestsTableView.getColumns().add(this.requestStatusColumn);
        this.friendshipsRequestsTableView.setPrefWidth(451);
        this.friendshipsRequestsTableView.setPrefHeight(257);
        this.requestUsernameColumn.setPrefWidth(this.friendshipsRequestsTableView.getPrefWidth() / 2);
        this.requestStatusColumn.setPrefWidth(this.friendshipsRequestsTableView.getPrefWidth() / 2);

        this.conversationArea = new ListView<>();
        this.conversationArea.setPrefHeight(257 + 64);
        this.conversationArea.setPrefWidth(451);
        this.conversationArea.setFixedCellSize(50);


        this.rightSectionVbox.getChildren().remove(this.underTableMessagePrompt);

        this.newConversationButton = new Button();
        this.newConversationButton.setId("newConvButton");
        this.newConversationButton.setPrefHeight(11);
        this.newConversationButton.setPrefWidth(125);
        this.newConversationButton.setLayoutX(37);
        this.newConversationButton.setLayoutY(12);
        this.newConversationButton.setText("Open Conversation");
        this.newConversationButton.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search-conversation-partner-view.fxml"));
            try {
                Parent root = loader.load();
                SearchConversationPartnerController searchConversationPartnerController = loader.getController();
                searchConversationPartnerController.initialize_everything(this.userService, this.userLoggedIn, this);
                Scene newScene = new Scene(root);
                newScene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("css_styles/search_conversation_partner_styles.css")).toExternalForm());

                Stage newStage = new Stage();
                newStage.setScene(newScene);
                newStage.show();
            } catch (IOException e) {
            }
        });
    }

    private void initModel() {
        this.getSearchResult();
        this.getFriendRequests();
    }

    @Override
    public void update(UserEntityChangeEvent userEntityChangeEvent) {
        if (Objects.equals(userEntityChangeEvent.getEventType().toString(), ChangeEventType.SEND_MESSAGE.toString())) {
            this.load_conversation();
        }
        else {
            this.initModel();
        }
    }

    public void onDisplayFriendRequests(ActionEvent actionEvent) {
        try {
            this.rightSectionVbox.getChildren().remove(this.searchResultTableView);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.friendshipsRequestsTableView);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.searchBar);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.underTableHbox);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.conversationArea);
        } catch (Exception e) {
        }
        try {
            this.statusBarRightPane.getChildren().remove(this.statusBarButton);
        } catch (Exception e) {
        }
        try {
            this.statusBarRightPane.getChildren().remove(this.newConversationButton);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.underTableMessagePrompt);
        } catch (Exception e) {
        }

        this.rightSectionVbox.getChildren().add(this.searchBar);
        this.rightSectionVbox.getChildren().add(this.friendshipsRequestsTableView);
        this.rightSectionVbox.getChildren().add(this.underTableHbox);
        this.statusBarLabel.setText("Search for new friends");
        this.statusBarRightPane.getChildren().add(this.statusBarButton);

        this.getFriendRequests();
    }

    public void onClickMenuHome(ActionEvent actionEvent) {
        try {
            this.rightSectionVbox.getChildren().remove(this.searchBar);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.friendshipsRequestsTableView);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.searchResultTableView);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.underTableHbox);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.conversationArea);
        } catch (Exception e) {
        }
        try {
            this.statusBarRightPane.getChildren().remove(this.statusBarButton);
        } catch (Exception e) {
        }
        try {
            this.statusBarRightPane.getChildren().remove(this.newConversationButton);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.underTableMessagePrompt);
        } catch (Exception e) {
        }

        this.rightSectionVbox.getChildren().add(this.searchBar);
        this.rightSectionVbox.getChildren().add(this.searchResultTableView);
        this.rightSectionVbox.getChildren().add(this.underTableHbox);
        this.statusBarRightPane.getChildren().add(this.statusBarButton);
        this.statusBarLabel.setText("Search for new friends");
    }

    public void onclickMenuMessages(ActionEvent actionEvent) {
        try {
            this.statusBarRightPane.getChildren().remove(this.statusBarButton);
        } catch (Exception e) {
        }
        try {
            this.statusBarRightPane.getChildren().remove(this.newConversationButton);
        } catch (Exception e) {
        }
        try {
            this.rightSectionVbox.getChildren().remove(this.underTableMessagePrompt);
        } catch (Exception e) {
        }
        if ((this.rightSectionVbox.getChildren().remove(this.friendshipsRequestsTableView) || this.rightSectionVbox.getChildren().remove(this.searchResultTableView)) && this.rightSectionVbox.getChildren().remove(this.underTableHbox) && this.rightSectionVbox.getChildren().remove(this.searchBar)) {
            this.statusBarLabel.setText("Conversations");
            this.rightSectionVbox.getChildren().add(this.conversationArea);
            this.rightSectionVbox.getChildren().add(this.underTableMessagePrompt);
            this.rightSectionVbox.getChildren().add(this.underTableHbox);
        }

        this.statusBarRightPane.getChildren().add(this.newConversationButton);
    }

    public void onActionSendMessage(ActionEvent actionEvent) {
        String message=this.messageTextField.getText();
        this.userService.send_message(this.userLoggedIn,this.currentConversationPartner,message);
        this.messageTextField.clear();
    }
}
