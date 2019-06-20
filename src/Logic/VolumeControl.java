package Logic;

import java.io.IOException;

public class VolumeControl{
    Runtime rt;
    private String pathToNircmdexe = "C:\\Users\\Niloufar Eshghi\\Desktop\\nircmd.exe";
    Process pr;

    public VolumeControl() throws IOException {
        rt = Runtime.getRuntime();
    }

    public void setSystemVolume(int volume)
    {
        if(volume < 0 || volume > 100)
        {
            throw new RuntimeException("Error: " + volume + " is not a valid number. Choose a number between 0 and 100");
        }

        else
        {
            double endVolume = 655.35 * volume;

            try
            {
                pr = rt.exec(pathToNircmdexe + " setsysvolume " + endVolume);
                pr = rt.exec(pathToNircmdexe + " mutesysvolume 0");

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}