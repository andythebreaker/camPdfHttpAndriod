package BookPage;

import android.os.Environment;

import org.json.JSONArray;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class Bookpage {
    public static ArrayList<HashMap<String, String>> book_page_aryList = new ArrayList<>();
    public static String book_page_aryList_2str(){
        String stuff2output="";
        ListIterator<HashMap<String, String>> listItr = book_page_aryList.listIterator();

        while(listItr.hasNext())
        {
            stuff2output+=listItr.next().get("Id")+", ";
        }
        return stuff2output;
    }/*
    // create object of Path
    Path path = Paths.get("D:/workspace/AmanCV.docx");

    // call getFileName() and get FileName path object
    Path fileName = path.getFileName();

    // print FileName
        System.out.println("FileName: "
                + fileName.toString());*/
    public static String book_page_file_name_list() throws JsonProcessingException {
        //建立JsonArray方法2
        ArrayList<String> json_arrayList = new ArrayList<String>();

        //String stuff2output="";
        ListIterator<HashMap<String, String>> listItr = book_page_aryList.listIterator();

        while(listItr.hasNext())
        {
            // create object of Path
            Path path = Paths.get(listItr.next().get("Id"));
            // call getFileName() and get FileName path object
            Path fileName_only = path.getFileName();
            //stuff2output+=fileName_only.toString()+", ";
            json_arrayList.add(fileName_only.toString());
        }

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json_str = ow.writeValueAsString(json_arrayList);

        return json_str;
    }
    public static String getBatchDirectoryName() {

        String app_folder_path = "";
        app_folder_path = Environment.getExternalStorageDirectory().toString() + "/images";
        File dir = new File(app_folder_path);
        if (!dir.exists() && !dir.mkdirs()) {

        }

        return app_folder_path;
    }
}
