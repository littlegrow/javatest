package rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import java.io.File;
import java.util.*;

public class RxjavaTest {

    /**
     * 搜集文件夹下所有文件
     */
    private static List<File> getFileList(String path) {
        List<File> allFile = new ArrayList<>();
        List<File> cacheList = new LinkedList<>();
        cacheList.add(new File(path));
        while (!cacheList.isEmpty()) {
            File file = cacheList.remove(0);
            if (file == null) {
                continue;
            }
            if (file.isDirectory()) {
                Collections.addAll(cacheList, Objects.requireNonNull(file.listFiles()));
            } else {
                allFile.add(file);
            }
        }
        return allFile;
    }

    /**
     * 过滤文件夹下所有 JAVA 文件，打印路径
     */
    private static void listFiles(String path) {
        Observable.just(path).flatMap((Function<String, ObservableSource<File>>) s -> Observable.fromIterable(getFileList(s)))
                .filter(file -> file.getName().endsWith(".java"))
                .subscribe(file -> System.out.println(file.getAbsolutePath()));
    }

    public static void main(String[] args) {
        listFiles("/Users/liuleigang/workplace/web/javatest/src");
    }
}
