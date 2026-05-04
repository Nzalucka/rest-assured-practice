package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Unrecognized field "name" (class model.CreateUserResponse)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CreateUserResponse {

        private String email;
        private String password;
        private String id;
        private String createdAt;

        public String getEmail()     {
            return email;
        }
        public String getPassword()  {
            return password;
        }
        public String getId()        {
            return id;
        }
        public String getCreatedAt() {
            return createdAt;
        }
    }

