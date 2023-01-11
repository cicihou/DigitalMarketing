/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.MarketModel;

import digitalmarketing.Business.Business;
import java.util.ArrayList;

/**
 *
 * @author cici
 */
public class ChannelDirectory {
    private ArrayList<Channel> channelArrayList;
    Business business;
    
    public ChannelDirectory(Business b) {
        channelArrayList = new ArrayList<>();
        business = b;
    }
    
    public Channel newChannel(String name) {
        Channel channel = new Channel(name);
        channelArrayList.add(channel);
        return channel;
    }
    
    public Channel findChannelByIndex(int index) {
        return channelArrayList.get(index);
    }
    
    public Channel findChannelById(String name) {
        for (Channel channel: channelArrayList) {
            if (channel.getChannelName().equals(name)) {
                return channel;
            }
        }
        return null;
    }
    
    public void printChannelsRevenue() {
        for (Channel channel: channelArrayList) {
            channel.printChannelRevenue();
        }
    }
    
    public void printChannelInfo() {
        System.out.println("------------------Channel------------------");
        for (Channel channel: channelArrayList) {
            System.out.print(channel.getChannelName() + ": ");
            channel.printAllMarket();
        }
        System.out.println();
    }
}
