
import edu.stanford.ejalbert.BrowserLauncher;
import edu.stanford.ejalbert.exception.BrowserLaunchingInitializingException;
import edu.stanford.ejalbert.exception.UnsupportedOperatingSystemException;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class OpenWebsites {

    private static String[] webpages;

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a stock ticker: ");
        String ticker = input.next();
        System.out.println();

        while(ticker != "0" && ticker != null) {
            ticker = ticker.toUpperCase(); // proper ticker format

            // Initialize webpages array; add any other URLs you like to use to this array
            webpages = new String[] {"http://www.marketwatch.com/investing/stock/" + ticker + "/analystestimates",
                                    "https://ycharts.com/companies/" + ticker,
                                    "https://ycharts.com/companies/" + ticker + "/fundamental_check",
                                    "https://ycharts.com/companies/" + ticker + "/value_check",
                                    "https://ycharts.com/companies/" + ticker + "/return_on_equity",
                                    "https://ycharts.com/companies/" + ticker + "/return_on_invested_capital",
                                    "https://ycharts.com/companies/" + ticker + "/debt_equity_ratio"
                                    };

            BrowserLauncher browserLauncher;

            // Open all the websites for this ticker
            try {
                browserLauncher = new BrowserLauncher(null);
                // Set browserLauncher to open a new window
                browserLauncher.setNewWindowPolicy(true);
                // Open first webpage in the new window
                browserLauncher.openURLinBrowser(webpages[0]);
                // Delays are necessary to ensure URLs open a new window at first, then open rest in that same new window
                TimeUnit.SECONDS.sleep(1);
                // Set browserLauncher to open new tabs in same window
                browserLauncher.setNewWindowPolicy(false);

                // Open the remaining webpages
                for(int i = 1; i < webpages.length; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    browserLauncher.openURLinBrowser(webpages[i]);
                }
            } catch (BrowserLaunchingInitializingException | UnsupportedOperatingSystemException e) {
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
        input.close();
    }
}
