package WebServer;

import BookPage.Bookpage;

import android.content.Context;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import fi.iki.elonen.NanoHTTPD;

public class MyWebServer extends NanoHTTPD {

    public final static int PORT = 33445;
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
        String uri = session.getUri();
        System.out.println("####MyWebServer:" + uri);
        String filename = uri.substring(1);

        boolean is_ascii = true;

        if (uri.equals("/"))
            filename = "index.html";
        //vendorsMainChunkTooLarge
        if (uri.equals("/book_list")){
            return newFixedLengthResponse(Response.Status.OK, "text/html", Bookpage.book_page_aryList_2str());
        }
        if (uri.equals("/book_list_file_name.json")){
            try {
                return newFixedLengthResponse(Response.Status.OK, "application/json", Bookpage.book_page_file_name_list());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (uri.contains("/bookpage")){
            InputStream isr2;
            try {
                Path pathjpg = Paths.get(uri);
                File initialFilejpg = new File(Bookpage.getBatchDirectoryName()+"/"+pathjpg.getFileName());
                isr2 = new FileInputStream(initialFilejpg);
                return newFixedLengthResponse(Response.Status.OK, "image/jpeg", isr2, isr2.available());
            } catch (IOException e) {
                e.printStackTrace();
                return newFixedLengthResponse(Response.Status.OK, "image/jpeg", "");
            }
        }

        String mimetype = "text/html";
        if (filename.contains(".html") || filename.contains(".htm")) {
            mimetype = "text/html";
            is_ascii = true;
        } else if (filename.contains(".js")) {
            mimetype = "text/javascript";
            is_ascii = true;
        } else if (filename.contains(".css")) {
            mimetype = "text/css";
            is_ascii = true;
        } else if (filename.contains(".gif")) {
            mimetype = "text/gif";
            is_ascii = false;
        } else if (filename.contains(".jpeg") || filename.contains(".jpg")) {
            mimetype = "image/jpeg";
            is_ascii = false;
        } else if (filename.contains(".png")) {
            mimetype = "image/png";
            is_ascii = false;
        } else {
            filename = "index.html";
            mimetype = "text/html";
        }

        if (uri.equals("/vendors_main.chunk.js"))
            is_ascii=false;

        if (is_ascii) {
            String response = "";
            String line = "";
            BufferedReader reader = null;
            try {
                if(Arrays.asList(_mainContext.getAssets().list("")).contains(filename)){
                    reader = new BufferedReader(new InputStreamReader(_mainContext.getAssets().open(filename)));

                    while ((line = reader.readLine()) != null) {
                        response += line+"\n";
                    }
                    reader.close();
                    return newFixedLengthResponse(Response.Status.OK, mimetype, response);
                }else{
                    return newFixedLengthResponse(Response.Status.OK, mimetype, "");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return newFixedLengthResponse(Response.Status.OK, mimetype, "");
            }
        } else {
            InputStream isr;
            try {
                isr = _mainContext.getAssets().open(filename);
                return newFixedLengthResponse(Response.Status.OK, mimetype, isr, isr.available());
            } catch (IOException e) {
                e.printStackTrace();
                return newFixedLengthResponse(Response.Status.OK, mimetype, "");
            }
        }
    }
}