package pl.niewiel;

import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Callable;


public class Kopiarka implements Callable<CopyInfo> {
    private String path1;
    private Path path, path2;

    Kopiarka(String path1) {
        this.path1 = path1;
        path = Paths.get(path1);
        path2 = Paths.get("plikiPrzekopiowane/" + removePath());

    }

    @Override
    public CopyInfo call() {
        try {
            Files.copy(path, path2, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new CopyInfo(path1);
    }


    @NotNull
    private String removePath() {
        return path1.substring(path1.lastIndexOf("/"));
    }
}
