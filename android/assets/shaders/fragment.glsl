varying vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_lightCoords;

uniform vec2 u_resolution;
uniform vec4 u_ambient;

uniform sampler2D u_texture;
uniform sampler2D u_lightmap;


void main() {
    vec4 color = texture2D(u_texture, v_texCoords); //koordinate su od 0.0 do 1.0 [xy / res]
    vec2 coords = gl_FragCoord.xy / u_resolution.xy;

    vec4 lightcolor = texture2D(u_lightmap, coords) ;//* vec4(0.3, 0.3, 0.7, 0.7);

/*
    if(lightcolor.rgb == vec3(1.0, 1.0, 1.0))
        lightcolor = vec4(1.0,1.0,1.0,1.0);
    else
        lightcolor = vec4(0.0,0.0,0.0,0.0);
        */

    //ambijentalna svjetlost
    vec3 amb = u_ambient.rgb * u_ambient.a; //rgb boja * intenzitet
    vec3 col = color.rgb * (amb + lightcolor.rgb);

    gl_FragColor = vec4(col, color.a) * v_color;
}