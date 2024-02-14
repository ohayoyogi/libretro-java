package org.ohayoyogi.libretro;

public interface LibRetroEventListener {
    void onKeyPressed(int keycode);
    void onKeyReleased(int keycode);
    void onUnload();
}
