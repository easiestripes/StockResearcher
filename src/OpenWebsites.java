
import edu.stanford.ejalbert.BrowserLauncher;
import edu.stanford.ejalbert.exception.BrowserLaunchingInitializingException;
import edu.stanford.ejalbert.exception.UnsupportedOperatingSystemException;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * TODO:
 * Put URLs in an array, then loop through it to open all webpages to make it easier to add more websites later on
 */

public class OpenWebsites {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a stock ticker: ");
        String ticker = input.next();
        System.out.println();

        while(ticker != "0" && ticker != null) {
            ticker = ticker.toUpperCase(); // proper ticker format

            BrowserLauncher browserLauncher;

            // Open all the websites for this ticker
            try {
                browserLauncher = new BrowserLauncher(null);
                // set browserLauncher to open a new window
                browserLauncher.setNewWindowPolicy(true);

                browserLauncher.openURLinBrowser("http://www.marketwatch.com/investing/stock/" + ticker + "/analystestimates");
                // delays are necessary to ensure URLs open a new window at first, then open rest in that same new window
                TimeUnit.SECONDS.sleep(1);

                // set browserLauncher to open new tabs in same window
                browserLauncher.setNewWindowPolicy(false);
                TimeUnit.SECONDS.sleep(1);
                browserLauncher.openURLinBrowser("https://ycharts.com/companies/" + ticker);
                TimeUnit.SECONDS.sleep(1);
                browserLauncher.openURLinBrowser("https://ycharts.com/companies/" + ticker + "/fundamental_check");
                TimeUnit.SECONDS.sleep(1);
                browserLauncher.openURLinBrowser("https://ycharts.com/companies/" + ticker + "/value_check");
                TimeUnit.SECONDS.sleep(1);
                browserLauncher.openURLinBrowser("https://ycharts.com/companies/" + ticker + "/return_on_equity");
                TimeUnit.SECONDS.sleep(1);
                browserLauncher.openURLinBrowser("https://ycharts.com/companies/" + ticker + "/return_on_invested_capital");
            } catch (BrowserLaunchingInitializingException e) {
                e.printStackTrace();
            } catch (UnsupportedOperatingSystemException e) {
                e.printStackTrace();
            }

            // Desktop is unable to control opening a new window first, then remaining URLs as tabs in that window
            /*if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(new URI("http://www.marketwatch.com/investing/stock/" + ticker + "/analystestimates"));
                        desktop.browse(new URI("https://ycharts.com/companies/" + ticker));
                        desktop.browse(new URI("https://ycharts.com/companies/" + ticker + "/return_on_equity"));
                        desktop.browse(new URI("https://ycharts.com/companies/" + ticker + "/return_on_invested_capital"));
                        desktop.browse(new URI("https://ycharts.com/companies/" + ticker + "/fundamental_check"));
                        desktop.browse(new URI("https://ycharts.com/companies/" + ticker + "/value_check"));
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    } catch (URISyntaxException use) {
                        use.printStackTrace();
                    }
                }
            }*/

            System.out.print("Enter another stock ticker: ");
            ticker = input.next();
            System.out.println();
        }
    }
}
