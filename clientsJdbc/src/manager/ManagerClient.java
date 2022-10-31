package manager;

import model.ConfigFile;
import util.ManagerConfig;

import java.io.File;

public class ManagerClient {
    private String url;
    private File file;
    private ConfigFile configFile;
    private ManagerForStartGetClient managerGet;
    private ManagerForStartPostClient managerPost;
    private boolean isActive;

    public ManagerClient(String url, String fileStr) {
        this.url = url;
        this.file = new File(fileStr);
        configFile = ManagerConfig.getConfigFromFile(file);

        isActive = true;
    }

    public void disable(){
        isActive = false;
        managerGet.disable();
        managerPost.disable();
    }

    public void startService() {
        if (isActive) {
            managerGet = new ManagerForStartGetClient(url, configFile);
            managerPost = new ManagerForStartPostClient(url, configFile);
            managerGet.start();
            managerPost.start();
        }

    }
}
