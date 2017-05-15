package hackfest_bismaoperation.com.hackfest_bismaoperation.Helper;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Converter;

/**
 * Created by Ryan Bagus Susilo on 5/12/2017.
 */
public class ToStringConverter implements Converter<String> {

    @Override
    public String fromBody(ResponseBody body)throws IOException {
        return body.string();
    }
    @Override
    public RequestBody toBody(String value){
        return RequestBody.create(MediaType.parse("text/plain"),value);

    }
}
