// OnChatInterface.aidl
package com.example.composesandbox;

// Declare any non-default types here with import statements

interface OnChatInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onChat(String author, String content);
}