varying vec4 v_color;
varying vec2 v_texCoords;

uniform vec2 u_res;
uniform sampler2D u_texture;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords) * v_color;
    vec3 ambient = vec3(0.0, 0.3, 0.5) * color.a;
    vec3 fin = color.rgb * ambient;

    gl_FragColor = vec4(fin, color.a);
}