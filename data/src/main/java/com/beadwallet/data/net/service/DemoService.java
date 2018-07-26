package com.beadwallet.data.net.service;

import com.beadwallet.data.bean.request.DemoRequest;
import com.beadwallet.data.bean.response.DemoResponse;
import com.beadwallet.data.net.ApiUrl;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface DemoService {
    @POST(ApiUrl.API_DEMO_URL)
    Observable<DemoResponse> request( @Body DemoRequest body);
}
