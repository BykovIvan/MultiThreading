package manager;

import client.AmountClientPost;
import model.ConfigFile;

public class ManagerForStartPostClient extends Thread {

    private String url;
    private ConfigFile configFile;
    private boolean isActive;

    public ManagerForStartPostClient(String url, ConfigFile configFile) {
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
            for (int j = 0; j < configFile.getwCount(); j++) {
                AmountClientPost clientPost = new AmountClientPost(url, configFile);
                clientPost.start();
            }
        }
    }
}
