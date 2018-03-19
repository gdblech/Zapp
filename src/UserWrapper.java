class UserWrapper {
    private User user;

    UserWrapper(){
        this.user = null;
    }
    UserWrapper(User user){
        this.user = user;
    }

    User getUser() {
        return user;
    }
    String  getFirstName(){
        if(this.user != null){
            return this.user.getFirstName();
        }else{
            return "null";
        }

    }
    String  getLastName(){
        if(this.user != null){
            return this.user.getLastName();
        }else{
            return "null";
        }

    }
    String  getEmail(){
        if(this.user != null){
            return this.user.getEmail();
        }else{
            return "null";
        }

    }
    String  getUsername(){
        if(this.user != null){
            return this.user.getUsername();
        }else{
            return "null";
        }
    }
    String getPassword(){
        if(this.user != null){
            return this.user.getPassword();
        }else{
            return "null";
        }
    }



    void setUser(User user) {
        this.user = user;
    }
    void setFirstName(String firstName){
        this.user.setFirstName(firstName);
    }
    void setLastName(String lastName){
        this.user.setLastName(lastName);
    }
    void setEmail(String email){
        this.user.setEmail(email);
    }
    void setUsername(String username){
        this.user.setUsername(username);
    }
    void setPassword(String password){
        this.user.setPassword(password);
    }
}


