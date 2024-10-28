package com.demo.account.domain.services.questions.verifiers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.http.HttpStatus;
import com.demo.account.domain.exception.ParametroIncorretoException;

public class DataFormatoBRVerifier implements Verifier {

    @Override
    public void verifyAnswer(String date) {
        String errMsgInvalidFormat =
                "Data não está com formato aceito. Considere o formato 27/02/1993";
        try {
            if (date == null || date.isEmpty())
                throw new ParametroIncorretoException(errMsgInvalidFormat, HttpStatus.BAD_REQUEST);
            if (!date.matches("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$"))
                throw new ParametroIncorretoException(errMsgInvalidFormat, HttpStatus.BAD_REQUEST);

            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date todayWithZeroTime = formatter.parse(formatter.format(new Date()));
            formatter.setLenient(true);
            Date convertedDate = formatter.parse(date);

            if (convertedDate.before(todayWithZeroTime))
                throw new ParametroIncorretoException("Data não deve ser anterior a hoje",
                        HttpStatus.BAD_REQUEST);
        } catch (ParseException e) {
            throw new ParametroIncorretoException(errMsgInvalidFormat, HttpStatus.BAD_REQUEST);
        }
    }

}
