from message_handler import MessageHandler
import presentation as ui

host = 'localhost'
port = 8080

HANDLER = MessageHandler(host, port)
print(HANDLER.login("iago").to_json())
print(HANDLER.send(data="Oi!", dst="Maria").to_json())
print(HANDLER.receive().to_json())
# while True:
#     if __CLIENT_MANAGER.is_logged():
#         ui.show_logged_user_menu()
#
#     else:
#         ui.show_unlogged_user_menu()