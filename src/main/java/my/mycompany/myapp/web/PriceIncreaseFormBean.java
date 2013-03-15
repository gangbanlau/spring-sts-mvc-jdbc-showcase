package my.mycompany.myapp.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceIncreaseFormBean {

	private static final Logger logger = LoggerFactory.getLogger(PriceIncreaseFormBean.class);

	@NotNull
	@Max(1000)
	@Min(-100)
    private int percentage;

    public void setPercentage(int i) {
        percentage = i;
        logger.debug("Percentage set to " + i);
    }

    public int getPercentage() {
        return percentage;
    }

}