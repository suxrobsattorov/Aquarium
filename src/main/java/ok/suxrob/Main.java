package ok.suxrob;

import ok.suxrob.service.FishService;

import static ok.suxrob.service.FishService.quit;

public class Main {
    public static void main(String[] args) {
        FishService.getInstance().run();

        while (!quit && FishService.getInstance().liveFishCount() > 0) {

            try {
                Thread.sleep(1000);
                FishService.getInstance().fishBorn();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
