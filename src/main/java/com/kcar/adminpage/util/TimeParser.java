package com.kcar.adminpage.util;

import com.kcar.adminpage.domain.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TimeParser{

    int searchTime;
    List<Question> offerInquiry;
    List<Question> itemInquiry;
    List<Question> clientInquiry;
    int[] time = new int[24];

    public TimeParser(List<Question> offerInquiry, List<Question> itemInquiry, List<Question> clientInquiry) {
        this.offerInquiry = offerInquiry;
        this.itemInquiry = itemInquiry;
        this.clientInquiry = clientInquiry;
    }
    
    public void filterHour(){
        for (Question question : offerInquiry) {
            if(question.getCreateDate().getHour() == 0){
                time[0] += 1;
            }
        }
    }

}
