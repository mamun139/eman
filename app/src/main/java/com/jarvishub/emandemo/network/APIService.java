package com.jarvishub.emandemo.network;

import com.jarvishub.emandemo.model.CreateOrderInfo;
import com.jarvishub.emandemo.model.OrderData;
import com.jarvishub.emandemo.model.User;
import com.jarvishub.emandemo.model.verification.VerificationResponse;
import com.jarvishub.emandemo.model.active_promotion.PromotionResponse;
import com.jarvishub.emandemo.model.apply_promo.PromoCodeResponse;
import com.jarvishub.emandemo.model.create_order.CreateOrderResponse;
import com.jarvishub.emandemo.model.eman_list.EmanListResponse;
import com.jarvishub.emandemo.model.order_details.OrderDetailsResponse;
import com.jarvishub.emandemo.model.order_track.TrackInfo;
import com.jarvishub.emandemo.model.service_packages.ServicePackagesResponse;
import com.jarvishub.emandemo.model.sign_out.SignOutResponse;
import com.jarvishub.emandemo.model.terms_conditions.TermsConditionsResponse;
import com.jarvishub.emandemo.network.login.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by mrrobot on 8/24/17.
 */

public interface APIService {

    /*
    * sign up fields
    *
    * */

/*    @Headers({
            "authorization:32DFCFD@#&DSFDSFSDF!L@?hh7@32DF"
    })*/

    @FormUrlEncoded
    @POST("signup")
    Call<SignUpResponse> createUser(
            @Field("device_id") String deviceId,
            @Field("first_name") String firstName,
            @Field("surname") String surnameName,
            @Field("mobile") String phone,
            @Field("email") String email,
            @Field("password") String password,
            @Field("dob") String dob,
            @Field("gender") String gender,
            @Field("type") String type
    );

    @FormUrlEncoded
    @POST("verify-code")
    Call<VerificationResponse> verifyClientMobile(
            @Field("code") String code
    );


    @Headers({"Content-Type: application/json"})
    @POST("login")
    Call<LoginResponse> createUser(@Body User user);

    //send co-ordinates to server
    @POST("eman-list")
    Call<EmanListResponse> emanList(
            @QueryMap HashMap<String, Double> coOrdinates);

    @POST("get-order-list")
    Call<OrderData> getOrderHistory();

    @FormUrlEncoded
    @POST("get-order")
    Call<OrderDetailsResponse>getOrderDetails( @Field("order_ref") String orderRef);


    @POST("track-order")
    Call<TrackInfo> getTrackOrderDetails();

    //getServices
    @POST("get-service")
    Call<ServicePackagesResponse> getServicePackages(
            @Body CoOrdinates coOrdinates);

    //create order
    @Headers({"Content-Type: application/json"})
    @POST("create-order")
    Call<CreateOrderResponse> createOrder(@Body CreateOrderInfo createOrder);

    //apply promo code
    @FormUrlEncoded
    @POST("apply-promocode")
    Call<PromoCodeResponse> applyPromoCode(@Field("code") String promoCode);

    @GET("page/terms-and-conditions")
    Call<TermsConditionsResponse>getTermsCondition();

    @POST("logout")
    Call<SignOutResponse>getSignOut();

    @POST("active-promotion")
    Call<PromotionResponse>getActivePromotion();
}
