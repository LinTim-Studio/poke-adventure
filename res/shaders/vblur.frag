#version 120

uniform sampler2D tex0;
uniform float resolution;
uniform float radius;

void main() {
	vec4 sum = vec4(0.0);
	vec2 tc = gl_TexCoord[0].st;
	float blur = radius/resolution;
	
	if (tc.y - 4 * blur > 0) {
	
	sum += texture2D(tex0, vec2(tc.x, tc.y - 4.0*blur)) * 0.05;
	sum += texture2D(tex0, vec2(tc.x, tc.y - 3.0*blur)) * 0.09;
	sum += texture2D(tex0, vec2(tc.x, tc.y - 2.0*blur)) * 0.12;
	sum += texture2D(tex0, vec2(tc.x, tc.y - 1.0*blur)) * 0.15;
	
	sum += texture2D(tex0, vec2(tc.x, tc.y)) * 0.16;
	
	sum += texture2D(tex0, vec2(tc.x, tc.y + 1.0*blur)) * 0.15;
	sum += texture2D(tex0, vec2(tc.x, tc.y + 2.0*blur)) * 0.12;
	sum += texture2D(tex0, vec2(tc.x, tc.y + 3.0*blur)) * 0.09;
	sum += texture2D(tex0, vec2(tc.x, tc.y + 4.0*blur)) * 0.05;

	gl_FragColor = vec4(sum.rgba);
	
	
	} else {
	// Der Rand soll nicht mit dem schwarzen verwischt werden
	
	sum += texture2D(tex0, vec2(tc.x, tc.y)) * (0.16 + 0.12);
	
	sum += texture2D(tex0, vec2(tc.x, tc.y + 1.0*blur)) * (0.15 + 0.1);
	sum += texture2D(tex0, vec2(tc.x, tc.y + 2.0*blur)) * (0.12 + 0.09);
	sum += texture2D(tex0, vec2(tc.x, tc.y + 3.0*blur)) * (0.09 + 0.06);
	sum += texture2D(tex0, vec2(tc.x, tc.y + 4.0*blur)) * (0.05 + 0.04);

	gl_FragColor = vec4(sum.rgba);
	}
}