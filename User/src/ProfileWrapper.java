class ProfileWrapper {
    private Profile profile;

    ProfileWrapper(){
        this.profile = null;
    }
    ProfileWrapper(Profile profile){
        this.profile = profile;
    }

    Profile getProfile() {
        return profile;
    }
    String  getFirstName(){
        if(this.profile != null){
            return this.profile.getFirstName();
        }else{
            return "null";
        }

    }
    String  getLastName(){
        if(this.profile != null){
            return this.profile.getLastName();
        }else{
            return "null";
        }

    }
    String  getEmail(){
        if(this.profile != null){
            return this.profile.getEmail();
        }else{
            return "null";
        }

    }
    String  getUsername(){
        if(this.profile != null){
            return this.profile.getUsername();
        }else{
            return "null";
        }
    }
    String getPassword(){
        if(this.profile != null){
            return this.profile.getPassword();
        }else{
            return "null";
        }
    }
    String getLocation(){
        if(this.profile != null){
            return this.profile.getLocation();
        }else{
            return "null";
        }
    }



    void setProfile(Profile profile) {
        this.profile = profile;
    }
    void setFirstName(String firstName){
        this.profile.setFirstName(firstName);
    }
    void setLastName(String lastName){
        this.profile.setLastName(lastName);
    }
    void setEmail(String email){
        this.profile.setEmail(email);
    }
    void setUsername(String username){
        this.profile.setUsername(username);
    }
    void setPassword(String password){
        this.profile.setPassword(password);
    }
}
