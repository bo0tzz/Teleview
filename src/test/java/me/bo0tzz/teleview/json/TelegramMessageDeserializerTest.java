package me.bo0tzz.teleview.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import me.bo0tzz.teleview.bean.TelegramMessage;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TelegramMessageDeserializerTest {

    private static final String SOURCE_JSON = "{\n" +
            "    \"ok\": true,\n" +
            "    \"result\": {\n" +
            "        \"_\": \"messages.peerDialogs\",\n" +
            "        \"dialogs\": [\n" +
            "            {\n" +
            "                \"_\": \"dialog\",\n" +
            "                \"pinned\": false,\n" +
            "                \"peer\": {\n" +
            "                    \"_\": \"peerChannel\",\n" +
            "                    \"channel_id\": 1006503122\n" +
            "                },\n" +
            "                \"top_message\": 69,\n" +
            "                \"read_inbox_max_id\": 0,\n" +
            "                \"read_outbox_max_id\": 0,\n" +
            "                \"unread_count\": 0,\n" +
            "                \"unread_mentions_count\": 0,\n" +
            "                \"notify_settings\": {\n" +
            "                    \"_\": \"peerNotifySettings\",\n" +
            "                    \"show_previews\": true,\n" +
            "                    \"silent\": false,\n" +
            "                    \"mute_until\": 0,\n" +
            "                    \"sound\": \"default\"\n" +
            "                },\n" +
            "                \"pts\": 118\n" +
            "            }\n" +
            "        ],\n" +
            "        \"messages\": [\n" +
            "            {\n" +
            "                \"_\": \"message\",\n" +
            "                \"out\": false,\n" +
            "                \"mentioned\": false,\n" +
            "                \"media_unread\": false,\n" +
            "                \"silent\": false,\n" +
            "                \"post\": true,\n" +
            "                \"id\": 69,\n" +
            "                \"to_id\": {\n" +
            "                    \"_\": \"peerChannel\",\n" +
            "                    \"channel_id\": 1006503122\n" +
            "                },\n" +
            "                \"date\": 1514731836,\n" +
            "                \"message\": \"Happy New Year, everyone! \uD83C\uDF89\\nI hope you enjoyed the Telegram product updates this December. Stay tuned – we are going to make 3 big announcements in January.\\n\\nOn a less joyful note, Iranian authorities started blocking Telegram in Iran today after we publicly refused to shut down channels of peaceful Iranian protesters, such as @sedaiemardom.\\n\\nWe are proud that Telegram is used by thousands of massive opposition channels all over the world. We consider freedom of speech an undeniable human right, and would rather get blocked in a country by its authorities than limit peaceful expression of alternative opinions.\\n\\nWhen it comes to freedom of speech, Telegram is as unrestricted as a mobile app can get. In 2015, after Apple and Google reached out to us in the aftermath of the Paris attacks, we added the simplest Terms of Service theoretically possible in an app: no calls for violence, no porn and no copyright infringement on public broadcast channels.\\n\\nSince then, Telegram has been blocking hundreds of violent public channels daily (including those reported in @isiswatch), making sure our rules are applied equally and fairly to all players, regardless of their size and political affiliation.   \\n\\nYesterday we had to suspend @amadnews, a public channel that started calling its subscribers to use Molotov cocktails and firearms against police. The admins of the channel reached out to us after the fact, apologizing for breaking our rules and pledging not to promote violence in the future. As a result, they have been able to reassemble most of their subscribers (800,000) in a new peaceful channel, which we welcomed. \\n\\nObviously, our neutrality and refusal to take sides in such conflicts can create powerful enemies. Iranian officials have filed criminal charges against me back in September for letting Telegram spread “uncensored news” and “extremist propaganda”. Today they imposed a block on Telegram – not clear whether permanent or temporary.\\n\\nAnd yet, doing the right thing is more important than trying to avoid having enemies. We’re extremely lucky to have been able to consistently apply our principles in 2017. We will continue doing it in 2018 – and beyond.\",\n" +
            "                \"entities\": [\n" +
            "                    {\n" +
            "                        \"_\": \"messageEntityMention\",\n" +
            "                        \"offset\": 330,\n" +
            "                        \"length\": 13\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"_\": \"messageEntityMention\",\n" +
            "                        \"offset\": 1073,\n" +
            "                        \"length\": 10\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"_\": \"messageEntityMention\",\n" +
            "                        \"offset\": 1239,\n" +
            "                        \"length\": 9\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"views\": 317771\n" +
            "            }\n" +
            "        ],\n" +
            "        \"chats\": [\n" +
            "            {\n" +
            "                \"_\": \"channel\",\n" +
            "                \"creator\": false,\n" +
            "                \"left\": true,\n" +
            "                \"editor\": false,\n" +
            "                \"broadcast\": true,\n" +
            "                \"verified\": true,\n" +
            "                \"megagroup\": false,\n" +
            "                \"restricted\": false,\n" +
            "                \"democracy\": false,\n" +
            "                \"signatures\": false,\n" +
            "                \"min\": false,\n" +
            "                \"id\": 1006503122,\n" +
            "                \"access_hash\": -5275695967390259389,\n" +
            "                \"title\": \"Durov's Channel\",\n" +
            "                \"username\": \"durov\",\n" +
            "                \"photo\": {\n" +
            "                    \"_\": \"chatPhoto\",\n" +
            "                    \"photo_small\": {\n" +
            "                        \"_\": \"fileLocation\",\n" +
            "                        \"dc_id\": 1,\n" +
            "                        \"volume_id\": 802332969,\n" +
            "                        \"local_id\": 41853,\n" +
            "                        \"secret\": 495500733304854338\n" +
            "                    },\n" +
            "                    \"photo_big\": {\n" +
            "                        \"_\": \"fileLocation\",\n" +
            "                        \"dc_id\": 1,\n" +
            "                        \"volume_id\": 802332969,\n" +
            "                        \"local_id\": 41855,\n" +
            "                        \"secret\": 852287360500353922\n" +
            "                    }\n" +
            "                },\n" +
            "                \"date\": 1446056458,\n" +
            "                \"version\": 0\n" +
            "            }\n" +
            "        ],\n" +
            "        \"users\": [\n" +
            "            {\n" +
            "                \"_\": \"user\",\n" +
            "                \"self\": true,\n" +
            "                \"contact\": false,\n" +
            "                \"mutual_contact\": false,\n" +
            "                \"deleted\": false,\n" +
            "                \"bot\": false,\n" +
            "                \"bot_chat_history\": false,\n" +
            "                \"bot_nochats\": false,\n" +
            "                \"verified\": false,\n" +
            "                \"restricted\": false,\n" +
            "                \"min\": false,\n" +
            "                \"bot_inline_geo\": false,\n" +
            "                \"id\": 369811608,\n" +
            "                \"access_hash\": -7548809712790831391,\n" +
            "                \"first_name\": \"B̷oet ͟\",\n" +
            "                \"last_name\": \"d҉e ̧W͏il͠l͢ig̀e̸ņ\",\n" +
            "                \"username\": \"bo0tzzz\",\n" +
            "                \"phone\": \"61423373340\",\n" +
            "                \"photo\": {\n" +
            "                    \"_\": \"userProfilePhoto\",\n" +
            "                    \"photo_id\": 1588328762497410985,\n" +
            "                    \"photo_small\": {\n" +
            "                        \"_\": \"fileLocation\",\n" +
            "                        \"dc_id\": 5,\n" +
            "                        \"volume_id\": 852119906,\n" +
            "                        \"local_id\": 199740,\n" +
            "                        \"secret\": -8333871743287730879\n" +
            "                    },\n" +
            "                    \"photo_big\": {\n" +
            "                        \"_\": \"fileLocation\",\n" +
            "                        \"dc_id\": 5,\n" +
            "                        \"volume_id\": 852119906,\n" +
            "                        \"local_id\": 199742,\n" +
            "                        \"secret\": -7985487409997712351\n" +
            "                    }\n" +
            "                },\n" +
            "                \"status\": {\n" +
            "                    \"_\": \"userStatusOffline\",\n" +
            "                    \"was_online\": 1515087147\n" +
            "                }\n" +
            "            }\n" +
            "        ],\n" +
            "        \"state\": {\n" +
            "            \"_\": \"updates.state\",\n" +
            "            \"pts\": 55,\n" +
            "            \"qts\": 0,\n" +
            "            \"date\": 1516462116,\n" +
            "            \"seq\": 1001,\n" +
            "            \"unread_count\": 1\n" +
            "        }\n" +
            "    }\n" +
            "}";

    @Test
    public void testDeserialize() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        TelegramMessage product = mapper.readValue(SOURCE_JSON, TelegramMessage.class);

        assertEquals("69", product.getId());
        assertTrue(product.isValid());

    }
}