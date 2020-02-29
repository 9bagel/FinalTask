package by.epam.learn.bahlei.finaltask.tag;

import by.epam.learn.bahlei.finaltask.entity.service.Service;
import by.epam.learn.bahlei.finaltask.util.Constants;
import by.epam.learn.bahlei.finaltask.util.LocaleUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
/**
 * Jstl custom tag class to represent service descriptions on multiple language
 */
public class DescriptionTag extends TagSupport {
    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(getLocalizedServiceDescription());
            return SKIP_BODY;
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    private String getLocalizedServiceDescription() {
        String locale = String.valueOf(pageContext.getSession().getAttribute(Constants.LOCALE));
        switch (LocaleUtil.getLocaleType(locale)) {
            case BE_BY:
                return service.getDescriptionBy();
            case RU_RU:
                return service.getDescriptionRu();
            default:
                return service.getDescriptionEn();
        }
    }
}
