package com.lapdao.service;

public interface SlackService {
	void send(String message);

	public static class SlackMessage {

		private String text;

		public SlackMessage() {
		}

		public SlackMessage(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

	}

}
