package com.demo.account.domain.services.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.http.HttpStatus;
import com.demo.account.domain.exception.ParametroIncorretoException;
import com.demo.account.domain.services.dto.admin.AdminQuestions;
import lombok.Data;

@Data
public class BigBallDTO {
    double value;
    String date;
    String teams;
    String hour;
    String numberId;
    AdminQuestions questions = new AdminQuestions();

    public BigBallDTO(double value, String date, String teams, String hour, String numberId) {
        this.value = value;
        this.date = date;
        this.teams = teams;
        this.hour = hour;
        this.numberId = numberId;
    }

    public BigBallDTO(String numberId) {
        this.numberId = numberId;
    }

    public BigBallDTO() {}

    public double setValue(double value) {
        if (value < 0)
            throw new ParametroIncorretoException("Valor do bolão não pode ser abaixo de 0",
                    HttpStatus.BAD_REQUEST);
        return value;
    }

    public String setDate(String date) {
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

            return date;
        } catch (ParseException e) {
            throw new ParametroIncorretoException(errMsgInvalidFormat, HttpStatus.BAD_REQUEST);
        }
    }

    public String setTeams(String teams) {
        if (teams == null || teams.isEmpty())
            throw new ParametroIncorretoException("É necessário definir os times",
                    HttpStatus.BAD_REQUEST);
        return teams;
    }

    public String setHour(String hour) {
        String errMsg = "Horas não está com formato aceito. Considere o formato 19:30";
        if (hour == null || hour.isEmpty() || !hour.matches("^(?:[01]?[0-9]|2[0-3]):[0-5][0-9]$"))
            throw new ParametroIncorretoException(errMsg, HttpStatus.BAD_REQUEST);

        return hour;
    }

    public AdminQuestions getQuestions() {
        return questions;
    }

    public void answerQuestion(int questionId, String value) {
        switch (questionId) {
            case 0:
                setTeams(value);
                break;
            case 1:
                setValue(Double.parseDouble(value));
                break;
            case 2:
                setDate(value);
                break;
            case 3:
                setHour(value);
                break;
            default:
                throw new ParametroIncorretoException("Essa questão não foi perguntada",
                        HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
