package pl.niewiel;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> pliki = new ArrayList<>();
        pliki.add("/home/niewiel/IdeaProjects/Uczelnia/trening2/plikiZrodlowe/plik1");
        pliki.add("/home/niewiel/IdeaProjects/Uczelnia/trening2/plikiZrodlowe/plik2");
        pliki.add("/home/niewiel/IdeaProjects/Uczelnia/trening2/plikiZrodlowe/plik3");
        pliki.add("/home/niewiel/IdeaProjects/Uczelnia/trening2/plikiZrodlowe/plik4");
        pliki.add("/home/niewiel/IdeaProjects/Uczelnia/trening2/plikiZrodlowe/plik5");

        ArrayList<Future<CopyInfo>> listaWynikow = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (String fName : pliki) {
            listaWynikow.add(executorService.submit(new Kopiarka(fName)));

        }
        executorService.shutdown();

        try {
            if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                for (Future<CopyInfo> info : listaWynikow) {
                    System.out.println("Plik " + info.get().getFileName()+" został skopiowany");
                }
            } else {
                System.out.println("Nie udało się skopiować");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

