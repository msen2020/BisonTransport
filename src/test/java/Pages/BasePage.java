package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.Drivers;

//Base page olusturmamizin amaci ortak kullandigimiz methodlari tekrar tekrar yamamak icin
public class BasePage {

    public BasePage(){

        PageFactory.initElements(Drivers.getDriver(), this);

        }
        public void waitforelements() throws InterruptedException{
            Thread.sleep(3000);

        }
            //Scroll down yapip webelement i buluyor
        public void scrollDown(WebElement Link){

        try {
            Thread.sleep(2000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
            JavascriptExecutor js = (JavascriptExecutor)Drivers.getDriver() ;
        /*
        /**
		 *  this script must scroll, until link element is visible
		 *  once link element visible, it will stop scrolling
		 *  arguments[0] = means first webelement after comma (link)
		 *  arguments it's an array of webelements after comma
		 *  arguments[0] = link web element, it can be any web element

         */
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            js.executeScript("arguments[0].scrollIntoView(true)", Link);
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
    }


}
