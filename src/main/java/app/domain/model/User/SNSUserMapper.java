package app.domain.model.User;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.Date;

public class SNSUserMapper {

    public SNSUserMapper(){

    }

    public ArrayList<UserDTO> toDTO(ArrayList<User> list){
        ArrayList<UserDTO> userList = new ArrayList<>();
        for (int i=0; i<list.size(); i++){
            User user = list.get(i);
            UserDTO userDTO = toDTO(user);
            userList.add(userDTO);
        }
        return userList;
    }

    public UserDTO toDTO (User user){
        int id = user.getId();
        String name = user.getName();
        String sex = user.getSex();
        Date birthDate = user.getBirthDate();
        String address = user.getAddress();
        int phoneNumber = user.getPhoneNumber();
        String eMail = user.getEmail();
        int snsNumber = user.getSnsNumber();
        int ccNumber = user.getCcNumber();
        return new UserDTO(id,name,sex,birthDate,address,phoneNumber,eMail,snsNumber,ccNumber);
    }

    public User toModel(UserDTO userDTO){
        int id = userDTO.getId();
        String name = userDTO.getName();
        String sex = userDTO.getSex();
        Date birthDate = userDTO.getBirthDate();
        String address = userDTO.getAddress();
        int phoneNumber = userDTO.getPhoneNumber();
        String eMail = userDTO.getEmail();
        int snsNumber = userDTO.getSnsNumber();
        int ccNumber = userDTO.getCcNumber();
        return new User(id,name,sex,birthDate,address,phoneNumber,eMail,snsNumber,ccNumber);
    }

}
