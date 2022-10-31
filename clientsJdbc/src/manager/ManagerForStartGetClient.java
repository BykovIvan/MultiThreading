package manager;

import client.AmountClientGet;
import model.ConfigFile;

public class ManagerForStartGetClient extends Thread {
    private String url;
    private ConfigFile configFile;
    private boolean isActive;

    public ManagerForStartGetClient(String url, ConfigFile configFile) {
        this.url = url;
        this.configFile = configFile;
        isActive = true;
    }

    public void disable(){
        isActive = false;
    }


    @Override
    public void run() {
        if (isActive) {
            for (int i = 0; i < configFile.getrCount(); i++) {
                AmountClientGet clientGet = new AmountClientGet(url, configFile);
                clientGet.start();
            }
        }
    }
}
