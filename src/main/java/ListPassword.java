import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ListPassword {


    public String[] getPassword(){
        File file = new File("C:\\Users\\79270\\Desktop\\java_qa\\LearQAAPI\\LearnQAAPI\\src\\main\\java\\password.txt");
        String[] passwords = new String[0];

        Set<String> targetSet = new HashSet<>();

        try(InputStream inputStream = new BufferedInputStream(new FileInputStream(file))){
            byte[] array = new byte[128];
            int count = inputStream.read(array);
            StringBuilder stringBuilder = new StringBuilder();
            while(count > 0){
                stringBuilder.append(new String(array, 0, count));
                count = inputStream.read(array);
            }
           passwords = stringBuilder.toString().split("\t|\r\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        Collections.addAll(targetSet, passwords);
        String[] result = new String[targetSet.size()];

        result = targetSet.toArray(result);

        return result;
    }
}
