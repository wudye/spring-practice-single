package gg.jte.generated.ondemand.pages;
import com.mwu.myoauth2.CsrfHiddenInput;
@SuppressWarnings("unchecked")
public final class JteloginGenerated {
	public static final String JTE_NAME = "pages/login.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,1,5,5,6,6,15,15,17,17,17,19,19,21,23,23,23,45,74,74,74,74,74,1,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Boolean error, String errorMessage, CsrfHiddenInput csrfHiddenInput) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtedefaultGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"min-h-screen flex items-center justify-center\">\r\n        <div class=\"max-w-md w-full space-y-8 p-8 bg-white rounded-lg shadow-md\">\r\n            <div>\r\n                <h2 class=\"mt-6 text-center text-3xl font-extrabold text-gray-900\">\r\n                    Sign in to your account\r\n                </h2>\r\n            </div>\r\n\r\n            ");
				if (error) {
					jteOutput.writeContent("\r\n                <div class=\"bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative\" role=\"alert\">\r\n                    <span class=\"block sm:inline\">");
					jteOutput.setContext("span", null);
					jteOutput.writeUserContent(errorMessage != null ? errorMessage : "An error occurred during login");
					jteOutput.writeContent("</span>\r\n                </div>\r\n            ");
				}
				jteOutput.writeContent("\r\n\r\n            ");
				jteOutput.writeContent("\r\n            <form class=\"mt-8 space-y-6\" action=\"/login\" method=\"POST\">\r\n                ");
				jteOutput.setContext("form", null);
				jteOutput.writeUserContent(csrfHiddenInput);
				jteOutput.writeContent("\r\n                <div class=\"rounded-md shadow-sm -space-y-px\">\r\n                    <div>\r\n                        <input name=\"username\" type=\"text\" required\r\n                               class=\"appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm\"\r\n                               placeholder=\"Username\">\r\n                    </div>\r\n                    <div>\r\n                        <input name=\"password\" type=\"password\" required\r\n                               class=\"appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm\"\r\n                               placeholder=\"Password\">\r\n                    </div>\r\n                </div>\r\n\r\n                <div>\r\n                    <button type=\"submit\"\r\n                            class=\"group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500\">\r\n                        Sign in\r\n                    </button>\r\n                </div>\r\n            </form>\r\n\r\n            ");
				jteOutput.writeContent("\r\n            <div class=\"mt-6\">\r\n                <div class=\"relative\">\r\n                    <div class=\"absolute inset-0 flex items-center\">\r\n                        <div class=\"w-full border-t border-gray-300\"></div>\r\n                    </div>\r\n                    <div class=\"relative flex justify-center text-sm\">\r\n                        <span class=\"px-2 bg-white text-gray-500\">Or continue with</span>\r\n                    </div>\r\n                </div>\r\n\r\n                <div class=\"mt-6 grid grid-cols-2 gap-3\">\r\n                    <a href=\"/oauth2/authorization/google\"\r\n                       class=\"w-full inline-flex items-center justify-center px-4 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-500 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500\">\r\n                        <img class=\"h-5 w-5 mr-2\" src=\"https://www.svgrepo.com/show/475656/google-color.svg\" alt=\"Google logo\">\r\n                        <span>Google</span>\r\n                    </a>\r\n\r\n                    <a href=\"/oauth2/authorization/github\"\r\n                       class=\"w-full inline-flex items-center justify-center px-4 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-500 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500\">\r\n                        <svg class=\"h-5 w-5 mr-2\" fill=\"currentColor\" viewBox=\"0 0 24 24\">\r\n                            <path fill-rule=\"evenodd\" d=\"M12 2C6.477 2 2 6.477 2 12c0 4.42 2.865 8.17 6.839 9.49.5.092.682-.217.682-.482 0-.237-.008-.866-.013-1.7-2.782.604-3.369-1.34-3.369-1.34-.454-1.156-1.11-1.464-1.11-1.464-.908-.62.069-.608.069-.608 1.003.07 1.531 1.03 1.531 1.03.892 1.529 2.341 1.087 2.91.832.092-.647.35-1.088.636-1.338-2.22-.253-4.555-1.11-4.555-4.943 0-1.091.39-1.984 1.029-2.683-.103-.253-.446-1.27.098-2.647 0 0 .84-.269 2.75 1.022A9.606 9.606 0 0112 6.82c.85.004 1.705.115 2.504.337 1.909-1.29 2.747-1.022 2.747-1.022.546 1.377.202 2.394.1 2.647.64.699 1.028 1.592 1.028 2.683 0 3.842-2.339 4.687-4.566 4.935.359.309.678.919.678 1.852 0 1.336-.012 2.415-.012 2.743 0 .267.18.578.688.48C19.137 20.167 22 16.42 22 12c0-5.523-4.477-10-10-10z\" clip-rule=\"evenodd\"></path>\r\n                        </svg>\r\n                        <span>GitHub</span>\r\n                    </a>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Boolean error = (Boolean)params.getOrDefault("error", false);
		String errorMessage = (String)params.getOrDefault("errorMessage", null);
		CsrfHiddenInput csrfHiddenInput = (CsrfHiddenInput)params.get("csrfHiddenInput");
		render(jteOutput, jteHtmlInterceptor, error, errorMessage, csrfHiddenInput);
	}
}
