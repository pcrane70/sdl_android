package com.smartdevicelink.api.speak;

import android.support.annotation.NonNull;

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

    /***
     * Sends the currently built TTS to be spoken aloud on the Sdl module.
     * @param context A SdlContext to be able to send from
     * @return Whether or not the TTS was sent to the Sdl module
     */
    public boolean send(@NonNull SdlContext context){
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

        /***
         * Adds a Text only TTS to the list of TTSChunks to be spoken by the Sdl module.
         * @param textToSpeak Text to be spoken
         * @return The builder for SdlTextToSpeak
         */
        public Builder addSpokenChunk(String textToSpeak){
            TTSChunk newChunk= new TTSChunk();
            newChunk.setText(textToSpeak);
            newChunk.setType(SpeechCapabilities.TEXT);
            mSpokenChunks.add(newChunk);
            return this;
        }

        /***
         * Adds a custom TTS to the list of TTSChunks that will be spoken by the Sdl module.
         * @param chunk Valid TTSChunk to be spoken
         * @return The builder for the SdlTextToSpeak
         */
        public Builder addSpokenChunk(TTSChunk chunk){
            mSpokenChunks.add(chunk);
            return this;
        }

        /***
         * Set multiple TTSChunks that will be said in order by the Sdl module
         * @param chunks A list of TTSChunks to be spoken
         * @return The builder for the SdlTextToSpeak
         */
        public Builder setSpokenChunks(@NonNull List<TTSChunk> chunks){
            mSpokenChunks = chunks;
            return this;
        }

        /***
         * Create the SdlTextToSpeak object that can be used
         * to sound off the added TTSChunks.
         * @return An SdlTextToSpeak object that is able
         * to send the TTS to the Sdl module.
         */
        public SdlTextToSpeak build(){
            return new SdlTextToSpeak(this);
        }

    }
}
