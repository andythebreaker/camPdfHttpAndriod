package BookPage;

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
    }
}
