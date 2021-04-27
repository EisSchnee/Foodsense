package FoodSense.Sorter;


/**
 * @author Benjamin Grimes
 */
public class ReorganizeView {

    private double progress;

    public ReorganizeView(){
        progress = 0;
    }
    /**
     * Displays the completion screen to the user
     */
    protected String displayCompletionScreen(){
        return "<h3>Complete</h2>";
    }

    /**
     * displays the progress bar screen
     */

    protected String displayProgressBar(){
        return "<h3>Progress </h2>" + "<progress value = \"" +
                ((int) progress) + "\" max = \"100\"/>";
    }

    /**
     * used to update the already created progress bar.
     *
     * @param progress
     * a double between 0 and 100 to show on the progress bar
     */
    protected void updateProgressBar(double progress){
        this.progress = progress;
    }


    protected String displayScreen(){
        String result;
        if(progress >= 100){
            result = displayCompletionScreen();
        }else{
            result = displayProgressBar();
        }
        return result;
    }
}
