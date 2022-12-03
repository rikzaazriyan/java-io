public class User {
    private Integer mrms_user_id;
    private String mrms_username;
    private String mrms_password;

    public String getMrms_password() {
        return mrms_password;
    }

    public void setMrms_password(String mrms_password) {
        this.mrms_password = mrms_password;
    }

    public String getMrms_username() {
        return mrms_username;
    }

    public void setMrms_username(String mrms_username) {
        this.mrms_username = mrms_username;
    }

    public Integer getMrms_user_id() {
        return mrms_user_id;
    }

    public void setMrms_user_id(Integer mrms_user_id) {
        this.mrms_user_id = mrms_user_id;
    }

    // @Override
    // public int compareTo(User u) {
    //     if (u.getMrms_username() == null) {
    //         return 0;
    //     }
    //     return getMrms_username().compareTo(u.getMrms_username());
    // }

}
