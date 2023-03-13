package com.rodrigoeo.login_retrofit2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    // @POST endpoint que se utiliza
    @POST("")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

}
