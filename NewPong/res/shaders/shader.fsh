#version 400 core

in vec2 pass_Texturecoord;

out vec4 out_Color;

uniform sampler2D model_Texture;
uniform vec3 color;

void main(void) {
	out_Color = vec4(color, texture(model_Texture, pass_Texturecoord).a);
}
