package pdr.chorumeblog.model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {

    USER(1);

    private final int code;

    public static RoleType valueOf(int code){
        for(RoleType value : RoleType.values()){
            if(value.getCode() == code){
                return value;
            }
        }

        throw new RuntimeException(); // create perso exception handler
    }


}
