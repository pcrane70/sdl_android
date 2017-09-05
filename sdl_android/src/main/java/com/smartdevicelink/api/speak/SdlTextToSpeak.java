package com.smartdevicelink.api.speak;

import com.smartdevicelink.api.interfaces.SdlContext;
import com.smartdevicelink.proxy.rpc.Speak;
import com.smartdevicelink.proxy.rpc.TTSChunk;
import com.smartdevicelink.proxy.rpc.enums.SpeechCapabilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mschwerz on 5/12/16.
 */
public class SdlTextToSpeak {

    private final ArrayList<TTSChunk> mSpokenChunks= new ArrayList<>();

    SdlTextToSpeak(Builder builder){
        mSpokenChunks.addAll(builder.mSpokenChunks);
    }

    public boolean send(SdlContext context){
        //no case yet to return false?
        //permissions should always be available?
        Speak newSpeak= new Speak();
        newSpeak.setTtsChunks(mSpokenChunks);
        context.sendRpc(newSpeak);
        return true;
    }

    public static class Builder{

        private List<TTSChunk> mSpokenChunks= new ArrayList<>();

        public Builder(){

        }

        public Builder addSpokenChunk(String textToSpeak){
            TTSChunk newChunk= new TTSChunk();
            newChunk.setText(textToSpeak);
            newChunk.setType(SpeechCapabilities.TEXT);
            mSpokenChunks.add(newChunk);
            return this;
        }

        public Builder addSpokenChunk(TTSChunk chunk){
            mSpokenChunks.add(chunk);
            return this;
        }

        public Builder setSpokenChunks(List<TTSChunk> chunks){
            mSpokenChunks = chunks;
            return this;
        }

        public SdlTextToSpeak build(){
            return new SdlTextToSpeak(this);
        }

    }
}
