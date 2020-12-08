import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class InLineKeyboard {
    private void setInline() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> firstButtonRow = new ArrayList<>();
        firstButtonRow.add(new InlineKeyboardButton().setText("Start").setCallbackData("/start"));
        firstButtonRow.add(new InlineKeyboardButton().setText("Help").setCallbackData("/help"));
        firstButtonRow.add(new InlineKeyboardButton().setText("Random anime picture").setCallbackData("/randomPicture"));

        List<InlineKeyboardButton> secondButtonRow = new ArrayList<>();
        secondButtonRow.add(new InlineKeyboardButton().setText("Weather").setCallbackData("/getWeather"));

        buttons.add(firstButtonRow);
        buttons.add(secondButtonRow);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(buttons);

    }
    public synchronized void answerCallbackQuery (String callbackId, String msg) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackId);
        answer.setText(msg);
        answer.setShowAlert(true);

//        try {
//            answerCallbackQuery(answer);
//        }
//        catch (TelegramApiException e){
//            e.printStackTrace();
//        }
    }
    }
