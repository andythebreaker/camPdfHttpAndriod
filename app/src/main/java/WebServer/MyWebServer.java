package WebServer;
import android.content.Context;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public class MyWebServer extends NanoHTTPD {

    private final static int PORT = 33445;
    private Context _mainContext;

    /*
    The main constructor, also used to start the http service
    */
    public MyWebServer(Context context) throws IOException {
        super(PORT);
        _mainContext = context;
        start();
        System.out.println("\nRunning! Point your browsers to [http://0.0.0.0:"+Integer.toString(PORT)+"/](http://localhost:"+Integer.toString(PORT)+"/)\n");
    }

    /*
    The main entry function for parsing, all requests come in and out from here
    */
    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h1>Hello server</h1>\n";
        msg += "<p>We serve " + session.getUri() + " !</p>";
        return newFixedLengthResponse( msg + "</body></html>\n" );
    }
}