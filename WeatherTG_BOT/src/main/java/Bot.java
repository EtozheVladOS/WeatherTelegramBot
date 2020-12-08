import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();                            //инициалзация ТГ АПИ
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();   //создаем объект типа message (msg) в который помещается сообщение из update
        String txt = msg.getText();
        Long chat_id = update.getMessage().getChatId();

        if (msg.getText() != null && msg.hasText()) {
            switch (txt) {
                case "Start":
                    sendMsg(msg, "Просто напишите имя города, для того чтобы узнать акутальный прогноз погоды. \n (Ого как неожиданно!)");
                    break;
                case "Help":
                    sendMsg(msg, "Если у вас есть какие-то вопросы по работе бота- просто напишите мне! \n @etozhevlados");
                    break;
                default:
                    try {
                        WeatherModel wm = new WeatherModel();
                        sendMsg(msg, Weather.getWeather(msg.getText(), wm));
                    } catch (IOException e) {
                        sendMsg(msg, "Это выдуманный город");
                    }
                    break;
            }
        }

    }


    private void sendMsg(Message msg, String text) {
        SendMessage send = new SendMessage();
        send.setChatId(msg.getChatId());
        send.setText(text);
        try {
            setBottoms(send);
            sendMessage(send);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setBottoms(SendMessage sendMessage) {
        ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(rkm); // Связываем клавиатуру с отправленным сообщением

        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Start"));

        KeyboardRow secondRow = new KeyboardRow();
        firstRow.add(new KeyboardButton("Help"));

        keyboardRowList.add(firstRow);
        keyboardRowList.add(secondRow);
        rkm.setResizeKeyboard(true); //Подгоняет клавиатуру под размер кнопок
        rkm.setKeyboard(keyboardRowList);
    }

    @Override
    public String getBotUsername() {
        return "WalriderExperementalBot";   //имя бота
    }

    @Override
    public String getBotToken() {
        return ""; //токен бота
    }
}
