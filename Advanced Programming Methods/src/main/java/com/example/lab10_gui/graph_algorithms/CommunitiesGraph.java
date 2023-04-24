package com.example.lab10_gui.graph_algorithms;


import com.example.lab10_gui.entities.User;

import java.util.*;

public class CommunitiesGraph {
    List<UserNode> graph=new ArrayList<>();
    List<Integer> distance= new ArrayList<>();

    List<List<UserNode>> communities= new ArrayList<>();
    int number_of_communities =0;


    /**
     * Builds the corresponding graph from a given list of users
     * @param users list of users
     */
    private void build_graph_from_user_list(Set<AbstractMap.SimpleEntry<Integer, User>> users) {
        this.graph.clear();
        for (int i=0; i<999; i++) {
            this.graph.add(null);
        }
        for(Map.Entry<Integer, User> user: users) {
            this.graph.set(user.getKey(), new UserNode(user.getValue()));
        }
    }

    private void initialize_distances() {
        for (int i=0; i<999; i++) {
            this.distance.add(0);
        }
    }

    /**
     * Performs Depth First Search algorithm starting from a given node
     * @param node should not be null
     */
    private void DFS(UserNode node, ArrayList<UserNode> community) {
        node.setVisited(true);
        for (User neighbour: node.getUser().getFriends()) {
            if (!graph.get(neighbour.getId()).isVisited()) {
                community.add(graph.get(neighbour.getId()));
                DFS(graph.get(neighbour.getId()), community);
            }
        }
    }

    /**
     * Returns the number of communities in the graph with a DFS based approach
     * @param users list of users
     * @return the number of communities formed by the users in the given list
     */
    public int get_number_of_communities(Set<AbstractMap.SimpleEntry<Integer, User>> users) {
        this.build_graph_from_user_list(users);
        for (UserNode node: this.graph) {
            if (node!=null && !node.isVisited()) {
                this.communities.add(new ArrayList<>());
                this.communities.get(number_of_communities).add(node);
                this.number_of_communities++;
                DFS(node, (ArrayList<UserNode>) this.communities.get(number_of_communities-1));
            }
        }
        return this.number_of_communities;
    }

    public void brute_force_find_longest_path(UserNode start_node, int path_length) {
        if (start_node.isVisited()) {
            return;
        }
        start_node.setVisited(true);

        if (this.distance.get(start_node.getUser().getId())<path_length) {
            this.distance.set(start_node.getUser().getId(), path_length);
        }

        for (User neighbour: start_node.getUser().getFriends()) {
            brute_force_find_longest_path(this.graph.get(neighbour.getId()), path_length+1);
        }

        start_node.setVisited(false);
    }

    private List<String> community_to_string(List<UserNode> nodes) {
        List<String> toStringCommunity= new ArrayList<>();
        for (UserNode userNode: nodes) {
            toStringCommunity.add(userNode.getUser().getUsername());
        }
        return toStringCommunity;
    }

    private void un_visit_all_nodes() {
        for (UserNode userNode: this.graph) {
            if (userNode!=null) {
                userNode.setVisited(false);
            }
        }
    }

    public List<String> get_longest_path(Set<AbstractMap.SimpleEntry<Integer, User>> users) {
        this.communities.clear();
        this.initialize_distances();

        this.get_number_of_communities(users);

        this.un_visit_all_nodes();

        UserNode start = null;
        for (UserNode userNode: this.graph) {
            if (userNode!=null) {
                start=userNode;
                break;
            }
        }

        if (start!=null) {
            this.brute_force_find_longest_path(start, 0);
        }

        for (UserNode node: this.graph) {
            if (node!=null && distance.get(node.getUser().getId())==0) {
                this.brute_force_find_longest_path(node, 0);
            }
        }
        int mx=-1;
        UserNode node=this.graph.get(0);

        for (UserNode userNode: this.graph) {
            if (userNode!=null && distance.get(userNode.getUser().getId())>mx) {
                mx=distance.get(userNode.getUser().getId());
                node=userNode;
            }
        }

        for (var community: this.communities) {
            for (UserNode userNode: community) {
                if (userNode!=null && Objects.equals(userNode.getUser().getId(), node.getUser().getId())) {
                    return this.community_to_string(community);
                }
            }
        }
        return null;
    }

}
