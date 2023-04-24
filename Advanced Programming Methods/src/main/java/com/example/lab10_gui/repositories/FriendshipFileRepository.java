//package com.example.lab10_gui.repositories;
//
//import com.example.lab10_gui.entities.Friendship;
//import com.example.lab10_gui.exceptions.RepositoryError;
//import com.example.lab10_gui.keys.TwoDIntegerKey;
//
//import java.io.*;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Map;
//import java.util.Objects;
//
//public class FriendshipFileRepository extends InMemoryRepository<TwoDIntegerKey, Friendship>{
//    private final String friendshipsFilePath;
//
//    public FriendshipFileRepository(String friendshipsFilePath) {
//        super();
//        this.friendshipsFilePath = friendshipsFilePath;
//        //this.read_all_from_file();
//    }
//
//    private Friendship extract_friendship(String[] friendship_info) {
//        Integer id_user1=Integer.parseInt(friendship_info[0]);
//        Integer id_user2=Integer.parseInt(friendship_info[1]);
//
//        LocalDate friendsFrom= LocalDate.parse(friendship_info[2]);
//        return new Friendship(id_user1, id_user2,friendsFrom);
//    }
//
//    private void read_all_from_file() {
//        try {
//            String splitBy=",";
//            BufferedReader bf_reader = new BufferedReader(new FileReader(this.friendshipsFilePath));
//            String line=bf_reader.readLine();
//
//            while(line!=null) {
//                String[] friendship_info=line.split(splitBy);
//                Friendship friendship= this.extract_friendship(friendship_info);
//                this.add(friendship);
//
//                line=bf_reader.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void write_all_to_file() {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(this.friendshipsFilePath));
//            PrintWriter pw= new PrintWriter(bw);
//
//            for (Map.Entry<TwoDIntegerKey, Friendship> entry: this.getAll()) {
//                pw.write(String.valueOf(entry.getKey().getKey1())+","+String.valueOf(entry.getKey().getKey2())+","+entry.getValue().getFriendsFrom()+"\n");
//            }
//            pw.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void append_to_file(Friendship friendship) {
//        try {
//            BufferedWriter bw= new BufferedWriter(new FileWriter(this.friendshipsFilePath,true));
//            PrintWriter pw = new PrintWriter(bw);
//
//            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//            pw.append(String.valueOf(friendship.getId().getKey1())).append(",").append(String.valueOf(friendship.getId().getKey2())).append(",").append(friendship.getFriendsFrom().format(dateTimeFormatter)).append("\n");
//            pw.close();
//        }
//        catch(IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void add_friendship(Integer id1, Integer id2) throws RepositoryError {
//        Friendship friendship=new Friendship(id1, id2);
//        if (this.getEntity(new TwoDIntegerKey(id1,id2))==null) {
//            this.add(friendship);
//            this.append_to_file(friendship);
//        }
//        else {
//            throw new RepositoryError("Already friends!\n");
//        }
//    }
//
//    public void remove_friendship(Integer id1, Integer id2) {
//        Friendship deletedFriendship=this.delete(new TwoDIntegerKey(id1, id2));
//        if (deletedFriendship!=null) {
//            this.deleted_entities.put(deletedFriendship.getId(),deletedFriendship);
//        }
//        this.write_all_to_file();
//    }
//
//    public void remove_all_friendships_belonging_to_a_user(Integer id) {
//        this.getAll().removeIf(entry-> (((Objects.equals(entry.getKey().getKey1(), id) || Objects.equals(entry.getKey().getKey2(),id))) && this.deleted_entities.put(entry.getKey(), entry.getValue())==null));
//        this.write_all_to_file();
//    }
//}
