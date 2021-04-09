package payload;

public class WelcomeMsg {
    public static String postWelcomeMessage() {
        return "{\n" +
                "  \"welcome_message\" : {\n" +
                "    \"name\": \"simple_welcome-message 01\",\n" +
                "    \"message_data\": {\n" +
                "      \"text\": \"This is Easha's DM. Welcome!\",\n" +
                "      \"attachment\": {\n" +
                "        \"type\": \"media\",\n" +
                "        \"media\": {\n" +
                "          \"id\": \"1380184472231022592\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
    }
}
