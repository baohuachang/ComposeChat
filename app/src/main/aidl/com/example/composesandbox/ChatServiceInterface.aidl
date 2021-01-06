// ChatServiceInterface.aidl
package com.example.composesandbox;
import com.example.composesandbox.OnChatInterface;

// Declare any non-default types here with import statements

interface ChatServiceInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void startChat(OnChatInterface chat);
}