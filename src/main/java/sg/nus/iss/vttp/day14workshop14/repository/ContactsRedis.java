package sg.nus.iss.vttp.day14workshop14.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import sg.nus.iss.vttp.day14workshop14.model.Contact;

@Repository
public class ContactsRedis {
    
    final String CONTACT_LIST = "contactList";
    
    @Autowired
    RedisTemplate<String, Object> template;
    
    public void saveContact(Contact contact, Model model) {
        //to insert record in list
        template.opsForList().leftPush(CONTACT_LIST, contact.getId());

        //to insert record in hash
        template.opsForHash().put(CONTACT_LIST+"_HASH", contact.getId(), contact);
        model.addAttribute("contact", contact);
    }
    
}
