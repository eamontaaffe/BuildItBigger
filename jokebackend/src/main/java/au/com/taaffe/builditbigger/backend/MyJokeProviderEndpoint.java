/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package au.com.taaffe.builditbigger.backend;

import com.example.JokeWizard;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.taaffe.com.au",
                ownerName = "backend.builditbigger.taaffe.com.au",
                packagePath = ""
        )
)
public class MyJokeProviderEndpoint {

    /**
     * A simple endpoint method that returns a joke
     */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {

        MyBean response = new MyBean();
        response.setData(JokeWizard.getJoke());

        return response;
    }

}
