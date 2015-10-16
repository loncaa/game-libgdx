//podaci dobiveni iz spriteBatch-a
attribute vec4 a_color;
attribute vec4 a_position;
attribute vec2 a_texCoord0;

uniform mat4 u_projTrans;

varying vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_lightCoords;

void main() {
    v_color = a_color;
    v_texCoords = a_texCoord0;

    //gl_FragCoord, koordinate u odnosu na cijeli koordinatni sustav Gdx.graphic.getWidth
    //batch ima transformacijsku matricu.
    gl_Position = u_projTrans * a_position;
    v_lightCoords = gl_Position.xy;
}