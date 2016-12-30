/**
 * Created by alexmadrzyk on 10/20/16.
 *
 * In this case, Config really means Achi board configuration.
 */
public class ConfigData {

	private String configuration;
	private int scoreVal;

	public ConfigData(String config, int score){
		configuration = config;
		scoreVal = score;
	}

	public String getConfig(){
		return configuration;
	}

	public int getScore(){
		return scoreVal;
	}
}
